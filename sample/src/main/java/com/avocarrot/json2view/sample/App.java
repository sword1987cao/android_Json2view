package com.avocarrot.json2view.sample;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.arthas.xjsonview.XViewComponent;
import com.arthas.xjsonview.XViewEngine;
import com.arthas.xjsonview.XViewImageAdapter;
import com.arthas.xjsonview.XiewConfig;
import com.arthas.xjsonview.bean.XViewBase;
import com.arthas.xjsonview.bean.XViewBody;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

import java.util.logging.Level;

/**
 * Created by zhangyn on 17/4/1.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);

        OkGo.init(this);
        OkGo.getInstance()

                // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                .debug("OkGo", Level.INFO, true)

                //如果使用默认的 60秒,以下三行也不需要传
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                .setCacheMode(CacheMode.NO_CACHE);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyAppName")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");

            XViewImageAdapter imageAdapter = new XViewImageAdapter() {

                @Override
                public void display(ImageView view, String src, XViewBase yiew) {
                    Glide.with(view.getContext()).
                            load(yiew.src).
                            crossFade(400)
                            .into(view);
                }
            };

            XiewConfig.setImageAdapter(imageAdapter);


        XiewConfig.addComponent("refreshBar", new XViewComponent() {

            @Override
            public View createComponentView(Context context, ViewGroup parent, XViewBody yiew ) {


                XViewBody template = XViewBody.create(XViewBody.RelativeLayout, XViewBody.MATCH,"48dp") ;

                XViewBody TextView = XViewBody.create(XViewBody.TextView, XViewBody.MATCH, XViewBody.MATCH) ;
                template.addChild(TextView);
                TextView.layout_centerInParent=true;
                TextView.text="刷新";
                TextView.gravity="center";
                TextView.onClick ="refresh";
                TextView.textColor="#333333";
                TextView.background="#ffffff";



                View yiew1 = XViewEngine.createView(context, parent, template);
                return yiew1;
            }

            @Override
            public void render(XViewBody yiew) {

            }
        });



        XiewConfig.addComponent("line", new XViewComponent() {

            @Override
            public View createComponentView(Context context, ViewGroup parent, XViewBody yiew ) {

                XViewBody dv = XViewBody.create(XViewBody.View, XViewBody.MATCH,"1px") ;
                dv.background = "#dddddd";
                View yiew1 = XViewEngine.createView(context, parent, dv);
                return yiew1;
            }

            @Override
            public void render(XViewBody yiew) {

            }
        });



    }
}
