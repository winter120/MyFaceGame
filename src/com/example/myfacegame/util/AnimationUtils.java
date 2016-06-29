package com.example.myfacegame.util;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationUtils {
	private  Animation animation;
	private static AlphaAnimation aa;
	private static RotateAnimation ra;
	private static ScaleAnimation sa;
	private static TranslateAnimation ta;
	/**
	 * 
	 * @param view  ��Ҫ����Ч����view
	 * @param time  ����������ʱ��
	 */
	public static void startAlphaAnim(View view,int time){
		aa=new AlphaAnimation(0.0f,1.0f);
		aa.setDuration(time);
		view.startAnimation(aa);
	}
	
	public static void startRotaAnim(View view ,int time){
		
		/**
		 * ��ʼ��ת�ĽǶȣ���ת�����ĽǶȣ���ǰX�ο�������ͣ��ο���X������,��ǰY�ο�������ͣ��ο���Y������
		 * Animation.ABSOLUTE     ������������
		 *  Animation.RELATIVE_TO_SELF   ���������������  ����0.5f�ͱ�ʾͼƬ���������
		 *  or Animation.RELATIVE_TO_PARENT   ����ڸ�������������
		 */
		ra=new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(time);
		view.startAnimation(ra);
	}
	
	public static void startScalAnim(View view,int time){
		sa=new ScaleAnimation(0.1f, 1.0f, 0.1f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
		sa.setDuration(time);
		view.startAnimation(sa);
	}
	
	public static void startTranAnim(View view, int time,int fromXType,int fromXValue,int toXType,int toXValue,int fromYType,int fromYValue,int toYType,int toYValue){
		ta=new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
		ta.setFillAfter(true);
		ta.setDuration(time);
		view.startAnimation(ta);
	}
	
	public static Animation startTransAnim2(View view,int time) {
		ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
		ta.setDuration(time);
		ta.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(ta);
		return ta;
	}
	
//	��ʼ�ƶ�����
	public static Animation startTransAnim3(View view,int time) {
		ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 2, Animation.RELATIVE_TO_PARENT, 1,Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
		ta.setDuration(time);
		ta.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(ta);
		return ta;
	}
	
//	���Ŷ���
	public static Animation startScaleAnim(View view,int time) {
		sa = new ScaleAnimation(0, 1.0f, 0, 1.0f);
		sa.setDuration(time);
		view.startAnimation(sa);
		return sa;
		
	}
	
//	��ʼ�ƶ�����
	public static Animation startTransAnim(View view,int time) {
		ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
		ta.setDuration(time);
		ta.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(ta);
		return ta;
	}
}
