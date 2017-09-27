package com.p4u.android.Http;

import android.graphics.Bitmap;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.BitmapConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import io.reactivex.Observable;

public class ServerApi {

//    public static Observable<String> getString(String header, String param) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("aaa", header);
//        HttpParams params = new HttpParams();
//        params.put("bbb", param);
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        return RxUtils.request(HttpMethod.GET, Urls.URL_METHOD, String.class, params, headers);
//    }
//
//    public static <T> Observable<T> getData(Type type, String url, String header, String param) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("aaa", header);
//        HttpParams params = new HttpParams();
//        params.put("bbb", param);
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        //这个RxUtils的封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
//        return RxUtils.request(HttpMethod.POST, url, type, params, headers);
//    }

    public static Observable<Response<Bitmap>> getWxBitmap(String url) {
        return OkGo.<Bitmap>post(url)//
                .converter(new BitmapConvert())//
                .adapt(new ObservableResponse<Bitmap>());
    }

//    public static Observable<Response<File>> getFile(String header, String param) {
//        return OkGo.<File>get(Urls.URL_DOWNLOAD)//
//                .headers("aaa", header)//
//                .params("bbb", param)//
//                .converter(new FileConvert())//
//                .adapt(new ObservableResponse<File>());
//    }
}
