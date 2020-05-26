package com.chengzhen.kotlinappother.mvp.net

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * 接口服务列表
 */
interface RetrofitService {

    //测试调用
    //    @GET("article/list/{page}/json")
    //    Observable<TestResponseBean> getArticle(@Path("page") int page);
    //
    //    //首页banner
    //    @GET("banner/json")
    //    Observable<HomeBannerResponseBean> getHomeBanner();
    //    //首页文章
    //    @GET("article/list/{page}/json")
    //    Observable<ArticleResponseBean> getHomeArticle(@Path("page") int page);
    //    //体系
    //    @GET("tree/json")
    //    Observable<SystemResponseBean> getKnowledgeSystem();
    //    //知识体系下的文章
    //    @GET("article/list/{page}/json")
    //    Observable<ArticleResponseBean> getKnowledgeArticleList(@Path("page") int page, @Query("cid") int id);
    //    //体系
    //    @GET("navi/json")
    //    Observable<NavigationResponseBean> getKnowledgeNavigation();
    //    //公众号主列表
    //    @GET("wxarticle/chapters/json")
    //    Observable<WeChatTopResponseBean> getWeChatTop();
    //    //公众号主详情列表
    //    @GET("wxarticle/list/{id}/{page}/json")
    //    Observable<WeChatListResponseBean> getWeChatList(@Path("id") int id, @Path("page") int page);
    //    //项目类型列表
    //    @GET("project/tree/json")
    //    Observable<ProjectResponseBean> getProjectTop();
    //    //项目列表
    //    @GET("project/list/{page}/json")
    //    Observable<ProjectListResponseBean> getProjectList(@Path("page") int page, @Query("cid") int id);
    //    //获取个人积分
    //    @GET("lg/coin/getcount/json")
    //    Observable<UserScoreResponseBean> getUserScore();
    //    //获取个人等级
    //    @GET("index")
    //    Observable<ResponseBody> getUserLevel();
    //     //登录 cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
    //    @FormUrlEncoded
    //    @POST("user/login")
    //    Observable<LoginResponseBean> getLogin(@Field("username") String username,
    //                                           @Field("password") String password);
    //    //注册
    //    @FormUrlEncoded
    //    @POST("user/register")
    //    Observable<LoginResponseBean> getRegister(@Field("username") String username,
    //                                              @Field("password") String password, @Field("repassword") String repassword);
    //    /**
    //     * 退出
    //     * 方法： GET
    //     * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
    //     * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。
    //     */
    //    @GET("user/logout/json")
    //    Observable<BaseRspBean> getLogout();
    //    //收藏文章列表
    //    @GET("lg/collect/list/{page}/json")
    //    Observable<CollectArticleresponseBean> getCollectArticleList(@Path("page") int page);
    //    //收藏网站列表
    //    @GET("lg/collect/usertools/json")
    //    Observable<CollectionLinkResponseBean> getCollectLinkList();
    //    //收藏站内文章
    //    @POST("lg/collect/{id}/json")
    //    Observable<BaseRspBean> collectArticle(@Path("id") int id);
    //    // 取消收藏 文章列表
    //    @POST("lg/uncollect_originId/{id}/json")
    //    Observable<BaseRspBean> unCollectArticle(@Path("id") int id);
    /* 取消收藏 我的收藏页面（该页面包含自己录入的内容）
     * id:拼接在链接上
     * originId:列表页下发，无则为-1
     * originId 代表的是你收藏之前的那篇文章本身的id； 但是收藏支持主动添加，这种情况下，没有originId则为-1
     */
    //    @FormUrlEncoded
    //    @POST("lg/uncollect/{id}/json")
    //    Observable<BaseRspBean> unMyCollect(@Path("id") int id, @Field("originId") int originId);
    //    //搜索热词
    //    @GET("hotkey/json")
    //    Observable<SearchHotBean> getHotKeyList();
    //    //搜索列表
    //    @FormUrlEncoded
    //    @POST("article/query/{page}/json")
    //    Observable<SearchListBean> search(@Path("page") int page,
    //                                      @Field("k") String key);


    //都是动态Url
    /**
     * @param url      请求子地址
     * @return         Retrofit2 的Observable对象
     */
    @GET
    fun getData(@Url url: String): Observable<ResponseBody>

    /**
     * @param url       请求子地址
     * @param params   请求参数
     * @return          Retrofit2 的Observable对象
     */
    @FormUrlEncoded
    @POST
    fun postData(@Url url: String, @FieldMap(encoded = true) params: Map<String, Any>): Observable<ResponseBody>

    /**
     * 图片上传
     * @param url        请求子地址
     * @param partMap   请求参数
     * @param paramfile      上传的file文件
     * @return           Retrofit2 的Observable对象
     */
    @Multipart
    @POST
    fun uploadImg(@Url url: String, @PartMap partMap: Map<String, RequestBody>, @Part paramfile: MultipartBody.Part): Observable<ResponseBody>

}
