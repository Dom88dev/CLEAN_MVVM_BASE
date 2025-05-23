package com.dom.presentation.base.dialog

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.viewbinding.ViewBinding
import com.dom.presentation.R

abstract class BaseDialog<T : ViewBinding, L : DialogListener> : AppCompatDialogFragment() {

    abstract val inflate: (inflater: LayoutInflater, container: ViewGroup?, b: Boolean) -> T

    protected lateinit var binding: T
        private set

    protected lateinit var listener: L

    // 스프링 애니메이션을 사용할때 이용할 뷰
    protected var animatedView: View? = null
    protected var offScreenTranslationY: Float = 0f // 화면 밖 Y 위치 (애니메이션 시작/종료 시 사용)


    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = inflate(inflater, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogFragmentTheme
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the AddPatientDialogListener so we can send events to the host
            listener = context as L
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement DialogListener")
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return binding.root.also { animatedView = it }
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.let { window ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // API 33 (Android 13)
                // 블러 효과를 사용하겠다는 플래그 추가 (이전에는 다른 의미로 사용되던 플래그 재활용)
                window.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
                // 블러 반경 설정 (0이면 블러 없음, 값은 픽셀 단위이며 기기 성능에 따라 최대치가 다를 수 있음)
                window.attributes.blurBehindRadius = 50 // 예시 값, 10~50 사이에서 적절히 조절
                // 변경된 속성 적용
                window.attributes = window.attributes
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.S_V2) { // API 32 (Android 12L)
                // Android 12L에서는 setBackgroundBlurRadius 사용 (픽셀 단위)
                window.setBackgroundBlurRadius(80) // 예시 값
                // 이 API는 Android 13의 setBlurBehindRadius로 대체되는 경향이 있습니다.
                // 가능하다면 API 33+ 방식을 우선 고려하세요.
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activity?.window?.setBackgroundBlurRadius(0)
        }
    }


    // 튀어 올라 오는 애니메이션을 쓰려면 onViewCreated() 에서 animatedView로 호출해서 사용.
    protected fun startSpringAnimation(view: View, appearing: Boolean) {
        if (appearing) {
            // 애니메이션 시작 전 초기 상태 설정: 화면 아래쪽 + 투명
            view.translationY = 2000f
//                view.alpha = 0f
            // 뷰가 레이아웃 된 후 실제 크기 및 위치를 알 수 있도록 post 사용
            // -> 하지만 post 로 하면 뷰의 y값을 적용해도 이미 늦음..
            view.post {
                // 화면 아래로 숨길 Y축 이동량 계산
                // 방법 1: 화면 높이만큼 아래로 (다이얼로그가 화면 중앙 등에 위치할 경우)
                // val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
                // offScreenTranslationY = screenHeight - animView.top // animView의 상단이 화면 하단에 오도록

                // 방법 2: 뷰 자체의 높이만큼 아래로 (다이얼로그가 화면 하단에 나타날 경우 더 적합)
                offScreenTranslationY = view.height.toFloat()

            }
        } else {
            offScreenTranslationY = view.height.toFloat()
        }

        // 목표 translationY: 나타날 때는 0 (원래 위치), 사라질 때는 화면 아래 (offScreenTranslationY)
        val targetTranslationY = if (appearing) 0f else offScreenTranslationY
        val targetAlpha = if (appearing) 1.0f else 0.0f

        // --- Y축 이동 스프링 애니메이션 ---
        val translationYAnimation = SpringAnimation(view, DynamicAnimation.TRANSLATION_Y).apply {
            spring = SpringForce(targetTranslationY).apply {
                // 강성과 댐핑 비율을 조절하여 원하는 '출렁임' 효과를 만듭니다.
                if (appearing) {
                    stiffness = SpringForce.STIFFNESS_LOW // 낮을수록 부드럽고 출렁임이 길어질 수 있음
                    dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY // 약간의 바운스
                } else {
                    stiffness = SpringForce.STIFFNESS_MEDIUM
                    dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY // 더 탄력있는 느낌
                }
            }
        }

        // --- 알파 (투명도) 스프링 애니메이션 ---
//        val alphaAnimation = SpringAnimation(view, DynamicAnimation.ALPHA).apply {
//            spring = SpringForce(targetAlpha).apply {
//                stiffness = SpringForce.STIFFNESS_VERY_LOW // 알파는 매우 부드럽게
//                dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY // 알파는 출렁임 없이
//            }
//        }

        // 사라지는 애니메이션의 경우, 모든 애니메이션 종료 후 실제 dismiss 처리
        if (!appearing) {
            var animationsFinished = 0
            val totalAnimations = 1 // 현재는 translationY와 alpha 두 개

            val endListener = DynamicAnimation.OnAnimationEndListener { _, _, _, _ ->
                animationsFinished++
                if (animationsFinished >= totalAnimations) {
                    // 모든 애니메이션 완료 후 DialogFragment 닫기
                    if (isAdded) { // 프래그먼트가 여전히 Activity에 추가된 상태인지 확인
                        super.dismissAllowingStateLoss()
                    }
                }
            }
            translationYAnimation.addEndListener(endListener)
//            alphaAnimation.addEndListener(endListener)
        }

        // 애니메이션 시작
        translationYAnimation.start()
//        alphaAnimation.start()
    }

    /**
     * 다이얼로그를 닫을 때 애니메이션과 함께 닫습니다.
     * 외부(예: 다이얼로그 내 닫기 버튼)에서 이 메소드를 호출합니다.
     */
    fun dismissWithAnimation() {
        animatedView?.let { animView ->
            // offScreenTranslationY가 계산되지 않았을 경우를 대비 (매우 드문 상황)
            if (offScreenTranslationY == 0f && animView.translationY == 0f) {
                offScreenTranslationY = animView.height.toFloat()
                if (offScreenTranslationY == 0f) { // 높이도 0이면 임의의 큰 값으로 (화면 밖으로 확실히)
                    offScreenTranslationY = 2000f
                }
            }
            startSpringAnimation(animView, false)
        } ?: run {
            // animatedView가 없으면 애니메이션 없이 바로 닫기
            if (isAdded) {
                super.dismissAllowingStateLoss()
            }
        }
    }
}