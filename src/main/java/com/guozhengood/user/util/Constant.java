package com.guozhengood.user.util;

public interface Constant {

    // true-测试，false-正式环境
    boolean                    debug                                         = true;

    String                     mobile_hide_default                           = "********";
    String                     mobile_hide_4                                 = "****";

    // 电商PC平台
    int                        platform_pc                                   = 1;

    // 系统日志前缀
    String                     syslog_dmbxg                                  = "[gzh]";

    // 系统管理员
    int                        user_type_vip_admin                           = 1;

    // 最低消费金额，包括返利起额
    public final static String pay_default_lower                             = "33";
    // 返利：需要冻结的比例，默认：10%
    public final static String fanli_frozen_rate                             = "0.1";
    public final static String pay_default_001                               = "0.01";

    // 本地IP
    public final static String ip_default_local                              = "127.0.0.1";

    // 推广链接前缀
    String                     top_prefix                                    = "pthui";
    String                     top_domain                                    = "http://www.pthui.com/anon/investorsOpen.htm?NO=";

    String                     pic_server_oss_online                         = "http://oss.dianmic.com/";
    String                     pic_server_oss_test                           = "http://oss-test.dianmic.com/";

    // 资源服务器地址
    String                     pic_server                                    = debug ? pic_server_oss_online
            : pic_server_oss_online;

    public static final String pic_dir_vip                                   = "vips";

    // excel导出单sheet最大记录数 + 1
    int                        excel_sheet_max_records                       = 50002;
    // excel列宽最大值限定为50个字符
    public final static int    excel_column_len_max                          = 50;

    // 上传文件的真实目录
    // public static final String upload_path_real = "E:/download/";
    public static final String upload_path_real_vip                          = "/home/web/res/" + pic_dir_vip + "/";
    public static final String upload_path_real_res                          = "/home/web/res/";
    public static final String upload_path_real_res_report                   = "/home/web/res/report/";

    // 图片缩略图前缀
    String                     pic_prefix_small                              = "m_";
    // 图片原图前缀
    String                     pic_prefix_original                           = "o_";
    // 小图尺寸
    int                        pic_small_width                               = 80;
    int                        pic_small_height                              = 80;

    // 文件上传目录：商城商品信息
    public static final String upload_path_real_shop_product                 = "/home/web/res/shop/product/";

    public static final String public_pic_vip                                = pic_server + pic_dir_vip + "/";

    // 登录成功，用户身份标识，2015年1月22日 22:27:45
    String                     cookie_key_suId                               = "dmbxg1";
    String                     cookie_key_code                               = "dmbxg2";
    String                     cookie_key_loginName                          = "dmbxg3";
    String                     cookie_key_loginIp                            = "dmbxg4";
    String                     cookie_key_type                               = "dmbxg5";

    // 缓存版本信息，2016年1月20日 17:26:10
    String                     cache_key_sysuser                             = "sysuser";
    String                     cache_key_ipWhite                             = "ipWhite";
    String                     cache_key_globalConfig                        = "globalConfig";
    String                     cache_key_company                             = "company";
    String                     cache_key_regionInfo                          = "regionInfo";
    String                     cache_key_chainInfo                           = "chainInfo";
    String                     cache_key_bankType                            = "bankType";
    String                     cache_key_bankInfo                            = "bankInfo";
    String                     cache_key_teamInfo                            = "teamInfo";
    String                     cache_key_shopsEwm                            = "shopsEwm";
    String                     cache_key_systemNotice                        = "systemNotice";
    String                     cache_key_mall                                = "mall";

    // 分页：默认每页显示条数
    int                        page_rows_default                             = 10;

    String                     key_current_uid                               = "uid";
    String                     key_current_loginName                         = "loginName";
    String                     key_current_activeUser                        = "activeUser";

    String                     md5_key_pass                                  = "dmagent@2018";

    // 审核认证：0-未认证，1-已认证，2-待审核
    Integer                    check_doing                                   = 0;
    Integer                    check_yes                                     = 1;
    Integer                    check_no                                      = 2;

    Integer                    status_active                                 = 1;
    Integer                    status_frozen                                 = 2;
    Integer                    status_delete                                 = 3;

    String                     project_title                                 = "易安适现金送达服务信息管理平台";

    String                     charset_utf8                                  = "UTF-8";

    String                     error_client                                  = "600";
    String                     error_server                                  = "500";
    String                     success                                       = "200";

    String                     split_douhao                                  = ",";
    String                     split_xie                                     = "/";

    String                     error_forward                                 = "errorInput";
    String                     page_forward_index                            = "index/index";

    /**
     * 照片上传的最大值，超过10M的不允许上传
     */
    int                        upload_file_size_max                          = 10000000;

    // 资源服务器路径
    String                     upload_file_path                              = "/home/web/res/";

    /**
     * 商品图片存放路径
     */
    String                     folderShopProduct                             = "shop/product";

    String                     formate_date_yyyyMMdd                         = "yyyyMMdd";

    String                     formate_date_time_default                     = "yyyy-MM-dd HH:mm:ss";

    String                     formate_date_yyyyMMddHHmmss                   = "yyyyMMddHHmmss";

    /**
     * 项目转移
     */

    // 代理商等级
    public static final Byte   province_dailishang_level                     = 1;                                                                        // 省级代理商
    public static final Byte   city_dailishang_level                         = 2;                                                                        // 市级代理商
    public static final Byte   county_dailishang_level                       = 3;                                                                        // 区县级代理商

    // 分销相关

    public static final Byte   FANLI_LEVEL_TYPE_1                            = 1;                                                                        // 一级返利
    public static final Byte   FANLI_LEVEL_TYPE_2                            = 2;                                                                        // 二级级返利
    public static final Byte   FANLI_LEVEL_TYPE_PROVINCE                     = 5;                                                                        // 省级代理商返利
    public static final Byte   FANLI_LEVEL_TYPE_CITY                         = 6;                                                                        // 市级代理商返利
    public static final Byte   FANLI_LEVEL_TYPE_COUNTY                       = 7;                                                                        // 区县级代理商返利

    public static final Byte   FANLI_TYPE_SHOP                               = 1;                                                                        // 购物返利
    public static final Byte   FANLI_TYPE_SHOP_DLS                           = 2;                                                                        // 购物上级返利
    public static final String FANLI_RATE_DEFAULT                            = "0.01";                                                                   // 返利乘积数

    // 缓存键值
    String                     CACHE_KEY_GLOBALCONFIG                        = "globalConfig";                                                           // 全局配置参数
    String                     CACHE_KEY_FANLI_GOODSPROFITS                  = "fanli_goodsprofits";                                                     // 利润配置版本信息缓存key
    String                     CACHE_KEY_USERINFO_COMPANY_TOP                = "cache_key_userinfo_company_top";                                         // 默认推荐人为公司账户

    String                     logger_prefix_login                           = "[登录]";
    String                     logger_prefix_logout                          = "[注销]";
    String                     logger_prefix_sysuser                         = "[系统用户]";
    String                     logger_prefix_company                         = "[公司管理]";
    String                     logger_prefix_shops                           = "[门店管理]";
    String                     logger_prefix_address                         = "[地址管理]";
    String                     logger_prefix_agent                           = "[代理商管理]";
    String                     logger_prefix_bankType                        = "[银行类型]";
    String                     logger_prefix_bankInfo                        = "[银行信息]";
    String                     logger_prefix_chainInfo                       = "[连锁信息]";
    String                     logger_prefix_regionInfo                      = "[区域信息]";
    String                     logger_prefix_teamInfo                        = "[团队信息]";
    String                     logger_prefix_orderCash                       = "[现金送达]";
    String                     logger_prefix_orderCoin                       = "[零钱兑换]";
    String                     logger_prefix_weixin                          = "[微信操作]";
    String                     logger_prefix_globalConfig                    = "[全局配置]";
    String                     logger_prefix_hc                              = "[耗材管理]";
    String                     logger_prefix_gis                             = "[GIS定位]";
    String                     logger_prefix_systemNotice                    = "[系统通知]";

    String                     logger_prefix_action_save                     = "[新增]";
    String                     logger_prefix_action_update                   = "[修改]";
    String                     logger_prefix_action_remove                   = "[删除]";

    String                     syslog_type_logou                             = "注销";
    String                     syslog_type_login                             = "登录";
    String                     syslog_type_company                           = "公司管理";
    String                     syslog_type_sysuser                           = "系统用户";
    String                     syslog_type_shops                             = "门店管理";
    String                     syslog_type_address                           = "地址管理";
    String                     syslog_type_agent                             = "代理商管理";
    String                     syslog_type_bankType                          = "银行类型";
    String                     syslog_type_bankInfo                          = "银行信息";
    String                     syslog_type_chainInfo                         = "连锁信息";
    String                     syslog_type_regionInfo                        = "区域信息";
    String                     syslog_type_teamInfo                          = "团队信息";
    String                     syslog_type_orderCash                         = "现金送达";
    String                     syslog_type_orderCoin                         = "零钱兑换";
    String                     syslog_type_weixin                            = "微信操作";
    String                     syslog_type_globalConfig                      = "全局配置";
    String                     syslog_type_hc                                = "耗材管理";
    String                     syslog_type_gis                               = "GIS定位";
    String                     syslog_type_systemNotice                      = "系统通知";
    String                     syslog_type_store                              = "仓库管理";

    int                        default_top                                   = 10000;
    String                     default_loginName                             = "admin";
    String                     default_loginPass                             = "000000";

    // 0SS SETTING
    String                     dir_root_dmbxg                                = "dmbxg/";
    // 产品图放置路径
    String                     dir_product_img                               = dir_root_dmbxg + "product/";
    // 用户图片上传路径
    String                     dir_user_img                                  = dir_root_dmbxg + "user/";
    // 通用图片上传路径
    String                     dir_common_img                                = dir_root_dmbxg + "common/";
    String                     dir_report                                    = dir_root_dmbxg + "report/";

    // 点微服务-微信号配置
    String                     weixin_dianmic_appid                          = "wx4a651a3e771b7e48";
    String                     weixin_dianmic_secret                         = "f3c91bdfb189b710a99992d04153e3ec";

    // session expire time: unit ms [timeout:-1000ms 永不超时]
    Long                       SESSION_EXPIRE_TIME                           = -10001L;

    String                     shiro_session_key_comId                       = "comId";
    String                     shiro_session_key_cartToOrderIds              = "cartToOrderIds";
    String                     shiro_session_key_orderListDfhFrom            = "orderListDfhFrom";
    String                     shiro_session_key_orderListDshFrom            = "orderListDshFrom";

    int                        default_company_id                            = 10004;
    String                     easyui_icon_shops_class                       = "icon-shops";

    String                     easyui_tree_root_shops                        = "门店信息";
    String                     easyui_tree_root_teams                        = "团队信息";
    String                     easyui_tree_root_banks                        = "银行信息";
    String                     easyui_tree_root_auth                         = "授权信息";
    String                     easyui_tree_root_mall                         = "商圈信息";
    String                     easyui_tree_root_category                         = "产品分类";

    String                     easyui_tree_shops_chain_prefix_id             = "c_";
    String                     easyui_tree_shops_region_prefix_id            = "r_";
    String                     easyui_tree_shops_bank_prefix_id              = "b_";
    String                     easyui_tree_shops_team_prefix_id              = "t_";
    String                     easyui_tree_shops_region_city_prefix_id       = "rc_";

    String                     easyui_tree_root_id                           = "0";

    String                     baidu_lbs_ak                                  = "ljnhXtDu8boAKVylHRyhqC9cKxQmGhWK";
    // address={address}&city={city}&
    String                     baidu_lbs_api_getLocation                     = "http://api.map.baidu.com/geocoder/v2/?output=json&ak="
            + baidu_lbs_ak;
    String                     baidu_lbs_api_daohang                         = "http://api.map.baidu.com/direction?mode=driving&output=html&src=dianmic";

    String                     fwry_workImg_default                          = "common/default.png";

    // 微信绑定密码卡密钥，2015年3月31日 17:14:53
    String                     weixin_user_bind_md5_key                      = "yas_weixin";
    String                     shops_bind_md5_key                            = "yas_shops";

    // 微信接口，2015年3月28日 20:31:33
    String                     weixin_access_token                           = "";
    Integer                    weixin_shId                                   = 1001;
    String                     weixin_shName                                 = "北京易安适科技有限公司";
    String                     weixin_appid                                  = "wxb0645c84ebccc1d2";
    String                     weixin_appsecret                              = "42aa064d99bbc411e7cbeed2fe551b37";
    Integer                    weixin_expires_in                             = 7200;
    String                     weixin_wxId                                   = "gh_df14524f95aa";

    String                     service_cash                                  = "现金送达";
    String                     service_coin                                  = "零钱兑换";
    String                     service_coin_apply                            = "兑零申请";

    String                     jpg_content_type                              = "image/jpeg";
    String                     png_content_type                              = "image/png";
    String                     jpg_suffix                                    = ".jpg";
    String                     png_suffix                                    = ".png";

    long                       picture_max_size                              = 1024 * 1024;
    int                        picture_cut_width                             = 1280;
    int                        picture_cut_height                            = 800;
    String                     dir_log                                       = "/home/web/logs/dianmic/dmbxg/";

    int                        redis_key_expire_one_day                      = 24 * 3600;
    String                     redis_key_prefix_weeks                        = "weeks_";

    String                     weixin_qiuyi                                  = "oKgXhvtV9O5TZhoSKVv0oMgU6tf4";
    String                     weixin_swf                                    = "oKgXhvuciX4tyoGj1c8o6CRCDgTg";

    int                        systemUser_type_observer                      = 6;
    int                        systemUser_type_shopsUser                     = 3;
    int                        systemUser_type_serviceUser                   = 2;
    int                        systemUser_type_client                        = 7;

    // 易安适服务管理平台域名
    String                     project_domain_pc                             = "http://easysecurity.com.cn";
    String                     project_domain_mobile                         = "http://easysecurity.com.cn";

    String                     globalConfig_key_orderCashAuditBackToShopsIn  = "orderCashAuditBackToShopsIn";

    String                     tomcat_main                                   = "tom6090yas";

    // 是否开放所有的门店二维码录入功能
    boolean                    isOpenAllShopsEwm                             = false;

    int                        ewmOpen_yes                                   = 1;
    int                        ewmOpen_no                                    = 0;
    int                        bankSaveType_oneByOne                         = 1;
    int                        bankSaveType_allToOne                         = 2;

    String                     shops_ewm_url_formate                         = pic_server_oss_online
            + "dmbxg/common/shops/%s.png";

    // 1周1次
    // int cash_frequency_oneWeek_oneTimes = 1;
    // 2周1次
    // int cash_frequency_twoWeek_oneTimes = 2;
    // 1周2次
    // int cash_frequency_oneWeek_twoTimes = 6;

    String                     cash_frequency_oneWeek_twoTimes_note          = "1周2次";

    String                     redis_key_userAuthShopsIds                    = "userAuthShopsIds_" + debug + "_%s";
    String                     redis_key_shopsEwmLogin_prefix                = "dmbxg:shopsEwmLogin:" + debug + "_%s";

    int                        redis_timeout_one_hour                        = 3600;
    int                        redis_timeout_one_day                         = 3600 * 24;
    int                        redis_timeout_default                         = redis_timeout_one_hour;

    String                     weixin_message_error_order_cash_bag_audit     = "小封包错误：[%s(%s)], [%s]营业款, 错误信息[%s].";

    // shopsStatus, 门店状态：2-待服务，1-服务中，3-服务终止，4-闭店
    int                        shopsStatus_service_doing                     = 1;
    int                        shopsStatus_service_waiting                   = 2;
    int                        shopsStatus_service_stop                      = 3;
    int                        shopsStatus_closed                            = 4;

    // 紫色
    String                     shops_two_weeks_once_shopsName_color          = "#EE82EE";
    String                     shops_two_weeks_once_shopsName_color_format   = "<span style='color:"
            + shops_two_weeks_once_shopsName_color + ";'>%s</span>";
    // 绿色
    String                     shops_one_weeks_two_shopsName_color_green     = "#008000";
    String                     shops_one_weeks_two_shopsName_color_format    = "<span style='color:"
            + shops_one_weeks_two_shopsName_color_green + ";'>%s</span>";
    // 蓝色
    String                     shops_three_weeks_once_shopsName_color_blue   = "#0000FF";
    String                     shops_three_weeks_once_shopsName_color_format = "<span style='color:"
            + shops_three_weeks_once_shopsName_color_blue + ";'>%s</span>";
    // 红色
    String                     shops_four_weeks_once_shopsName_color_red     = "#FF0000";
    String                     shops_four_weeks_once_shopsName_color_format  = "<span style='color:"
            + shops_four_weeks_once_shopsName_color_red + ";'>%s</span>";

    // 重载页面Title
    String page_title_key = "page_title";

    // 管理员
    int USER_TYPE_SUPER_ADMIN = 1;

    // 平台用户
    int USER_TYPE_COMMON_USER = 2;
}
