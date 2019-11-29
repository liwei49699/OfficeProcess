package com.yvrun.officeprocess.mvp.net;

import com.yvrun.officeprocess.core.primary.home.HomeArticelResponseBean;
import com.yvrun.officeprocess.mvp.model.bean.TestResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * 接口服务列表
 */
public interface RetrofitService {

    @GET("article/list/{page}/json")
    Observable<TestResponseBean> getArticle(@Path("page") int page);


    @GET("article/list/{page}/json")
    Observable<HomeArticelResponseBean> getHomeArticle(@Path("page") int page);

    //都是动态Url
    /**
     * @param url      请求子地址
     * @return         Retrofit2 的Observable对象
     */
    @GET
    Observable<ResponseBody> getData(@Url String url);

    /**
     * @param url       请求子地址
     * @param params   请求参数
     * @return          Retrofit2 的Observable对象
     */
    @FormUrlEncoded
    @POST()
    Observable<ResponseBody> postData(@Url String url, @FieldMap(encoded = true) Map<String, Object> params);

    /**
     * 图片上传
     * @param url        请求子地址
     * @param partMap   请求参数
     * @param paramfile      上传的file文件
     * @return           Retrofit2 的Observable对象
     */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadImg(@Url String url, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part paramfile);

}
