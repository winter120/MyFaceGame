package com.example.myfacegame.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Identity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.myfacegame.R;
import com.example.myfacegame.adapter.GridAdapter;
import com.example.myfacegame.listener.IDecorationChangedListener;
import com.example.myfacegame.util.MyRes;
import com.example.myfacegame.view.MainView;

public class PagerFragment extends Fragment{
	
	private View view;
	private List<Integer> resIds=new ArrayList<>();//ͼƬ��Դ
	private Context context;
	private GridView gridView;
	private int index;//��ǰviewpager�����
	private int ids[];//ͼƬ��Դid
	private boolean sex;
	private IDecorationChangedListener idcListener;
	/**
	 * ��֮������activity��oncreate��������ô˷���
	 */
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initGridView();
	}
	
	/**
	 * fragment������ʱ����
	 */
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.layout_fragment, container, false);
		return view;
	}

	private void initGridView() {
		context=getActivity();
		Bundle bundle=getArguments();
		index=bundle.getInt("index");
		sex=bundle.getBoolean("sex");
		gridView=(GridView) view.findViewById(R.id.gridView);
		getData();
		GridAdapter gridAdapter=new GridAdapter(resIds, context);
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				idcListener.onchanged(index, resIds.get(position));
			}
		});
	}
	/**
	 * ���ݵ�ǰ�����õ���ǰposition����ȡ��ǰ����Դ
	 */
	private void getData() {
		switch (index) {
		case 0://����
			if(sex){
				ids=MyRes.getRealBoyHair();
			}else {
				ids=MyRes.getRealGirlHair();
			}
			break;
		case 1://����
			ids=MyRes.getFaceShape();
			break;
		case 2://üë
				ids=MyRes.getEyeBrow();
			break;
		case 3://�۾�
			ids=MyRes.getEye();
			break;
		case 4://���
			ids=MyRes.getMouth();
			break;
		case 5://����
			if(sex){
				ids=MyRes.getRealBoyFeature();
			}else {
				ids=MyRes.getRealGirlFeature();
			}
			break;
		case 6://�۾�
			ids=MyRes.getGlass();
			break;
		case 7://�·�
			if(sex){
				ids=MyRes.getBoyClothes();
			}else {
				ids=MyRes.getGirlClothes();
			}
			break;
		case 8://ñ��
				ids=MyRes.getHat();
			break;
		case 9://����
			ids=MyRes.getHobby();
			break;
		case 10://����
			ids=MyRes.getBackGround();
			break;
		case 11://����
			ids=MyRes.getPop();
			break;
			
		default:
			break;
		}
		
		for (int i = 0; i < ids.length; i++) {
			resIds.add(ids[i]);
		}
		
	}
	
	public void setChangedListener(IDecorationChangedListener idcListener){
		this.idcListener=idcListener;
	}
	
}
