package com.example.myfacegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfacegame.adapter.FragmentAdapter;
import com.example.myfacegame.view.MainView;

public class MainActivity extends FragmentActivity {

	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private HorizontalScrollView hsv;// ˮƽ������
	private ViewPager viewPager;
	private TextView cursor;// �α�
	private boolean sex;
	private Button bt_back,bt_save;
	private MainView mainView;//�Զ����ͼ
	private RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initHsv();
		initSex();
		// ���Ի�tabѡ����
		initTab();
		// ��ʼ���α�
		initCursor();
		// ���Ի�viewpager
		initView();
		initPager();
		initButton();
	}
	/**
	 * �������Ļ�������
	 */
	private void initView() {
		mainView=new MainView(this, sex);
		rl=(RelativeLayout) findViewById(R.id.rl);
		rl.addView(mainView);
	}

	private void initButton() {
		bt_back=(Button) findViewById(R.id.bt_back);
		bt_save=(Button) findViewById(R.id.bt_save);
		
		bt_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		bt_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Bitmap bitmap=mainView.getDrawingCache();
				File file =new File(Environment.getExternalStorageDirectory(), "lianMeng.png");
				try {
					bitmap.compress(CompressFormat.PNG, 100, new FileOutputStream(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ��ʼ��ˮƽ������
	 */
	private void initHsv() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
	}

	/**
	 * ��ȡ�Ա�
	 */
	private void initSex() {
		Intent intent = getIntent();
		sex = intent.getBooleanExtra("sex", true);
	}

	/**
	 * ��ʼ���α�
	 */
	private void initCursor() {
		cursor = (TextView) findViewById(R.id.cursorview);
	}

	/**
	 * ���Ի�viewpager
	 */
	@SuppressWarnings("deprecation")
	private void initPager() {
		// ��ȡ�ؼ�
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		FragmentAdapter fragmentAdapter = new FragmentAdapter(
				getSupportFragmentManager(),sex,mainView);
		// ��������
		viewPager.setAdapter(fragmentAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// position��ʾ��ǰҳ������
				/**
				 * positionOffset ��ǰ��������
				 * ����������һ�����ôoffset��0��1(��ҳ���Ϊ0).���������󻮣���ôoffset��1��0
				 */
				// Log.d("onPageScrolled",
				// "pos="+position+"    offset="+positionOffset);
				int radioBtWidth = radioButton.getWidth();
				int newposition = (int) (position * radioBtWidth + positionOffset
						* radioBtWidth);
				int center = (viewPager.getWidth() - radioBtWidth) / 2;
				hsv.scrollTo(newposition - center, 0);
				// Log.d("onPageScrolled",
				// "newpos="+newposition+"   scrollto="+(newposition-center));
				startMoveCursor(position, positionOffset);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

	}

	private int from = 0;

	/**
	 * �����α��ƶ�
	 * 
	 * @param position
	 *            ��ǰ�ƶ���λ��
	 * @param positionOffset
	 *            ��ǰ�ƶ��İٷֱ�
	 */
	protected void startMoveCursor(int position, float positionOffset) {
		// ��ȡ��ǰ��ѡ�е�radiobutton
		RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
		// �����������ȵ����� 0�±� ����x���� 1�±�y����
		int[] location = new int[2];
		// ��ȡ��ǰradiobutton������
		rb.getLocationInWindow(location);

		// �����ƶ��������
		int to = (int) (location[0] + positionOffset * rb.getWidth());

		// ��������
		TranslateAnimation ta = new TranslateAnimation(from, to, 0, 0);
		ta.setDuration(100);
		// ��������Ժ�ͣ���ڵ�ǰ������λ��
		ta.setFillAfter(true);

		cursor.startAnimation(ta);
		from = to;

	}

	/**
	 * ���Ի�tabѡ����
	 */
	private void initTab() {
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioButton = (RadioButton) findViewById(R.id.rb1);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb1:
					viewPager.setCurrentItem(0);
					break;
				case R.id.rb2:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rb3:
					viewPager.setCurrentItem(2);
					break;
				case R.id.rb4:
					viewPager.setCurrentItem(3);
					break;
				case R.id.rb5:
					viewPager.setCurrentItem(4);
					break;
				case R.id.rb6:
					viewPager.setCurrentItem(5);
					break;
				case R.id.rb7:
					viewPager.setCurrentItem(6);
					break;
				case R.id.rb8:
					viewPager.setCurrentItem(7);
					break;
				case R.id.rb9:
					viewPager.setCurrentItem(8);
					break;
				case R.id.rb10:
					viewPager.setCurrentItem(9);
					break;
				case R.id.rb11:
					viewPager.setCurrentItem(10);
					break;
					
				default:
					break;
				}
			}
		});
	}
}
