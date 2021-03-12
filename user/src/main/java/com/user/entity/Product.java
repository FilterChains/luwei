package com.user.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>@description : 商品搜索实体类 </p>
 * <p>@author : Wei.Lu </p>
 * <p>@date : 2021/3/8 10:44 </p>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "lu_wei_product", type = "test_search")
public class Product implements Serializable {

    /**
     * 商品id
     */
    @Id
    private String id;

    /**
     * 商品类型：1-普通商品，2-控销商品，3-商品包
     */
    private Integer kind;
    /**
     * 商品名称
     */
//    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String name;
    /**
     * 药品品类id
     */
    private Integer aptitudeId;
    /**
     * 商品类型id
     */
    private Integer typeId;
    /**
     * 商品分类id树，如[1][7][9]
     */
    private String typeIdTree;
    /**
     * 是否上架
     */
    private Boolean onshelf;
    /**
     * 主图地址
     */
    private String imageUrl;
    /**
     * 批准文号
     */
//    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String licenseNumber;
    /**
     * 规格
     */
//    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String specifications;
    /**
     * 件装量
     */
    private String pieceLoad;
    /**
     * 生产企业生产厂家
     */
//    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String manufactur;
    /**
     * 生产厂家id
     */
    private Integer manufacturId;
    /**
     * 功能主治
     */
    private String indication;
    /**
     * 主要成分
     */
    private String components;
    /**
     * 用法用量
     */
    private String pUsage;
    /**
     * 不良反应
     */
    private String reaction;
    /**
     * 注意事项
     */
    private String attention;
    /**
     * 禁忌说明
     */
    private String avoid;
    /**
     * 单位
     */
    private String uinit;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 商品资质
     */
    private String certificate;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 最后更新人id
     */
    private Integer updateBy;
    /**
     * 新品推荐值：默认为0，值越大排名越靠前
     */
    private Integer newRecommend;
    /**
     * 热销推荐：默认为0，值越大排名越靠前
     */
    private Integer hotRecommend;
    /**
     * 精品专区：默认为0，值越大排名越靠前
     */
    private Integer fineRecommend;
    /**
     * 商品分类关联商品推荐：默认为0，值越大排名越靠前
     */
    private Integer typeRecommend;
    /**
     * 高毛推荐：默认为0，1为推荐
     */
    private Integer highRecommend;
    /**
     * 总库存
     */
    private Integer stock;
    /**
     * 最小购买量
     */
    private Integer minBuy;
    /**
     * 最大购买量
     */
    private Integer maxBuy;
    /**
     * 排序值，值越大越靠前
     */
    private Integer sort;
    /**
     * 商品标签id,如[1][2]
     */
    private String tags;
    /**
     * 预定商品标记字段 1-预定 ，默认0-不是预定商品
     */
    private Integer book;
    /**
     * 销售对象IDS集合如:[1][2][3]
     */
    private String salestarget;
    /**
     * 云采对比浪嘉系统换算比例，默认为1:1，如果设置500,云采一个抵浪嘉500个
     */
    private Integer ratio;
    /**
     * 商品审核状态，0-未审核，1-审核通过，2-审核未通过
     */
    private ReviewStatusEnum status;

    public enum ReviewStatusEnum {
        /**
         * 状态：0-待审核
         */
        PEND(0, "待审核"),
        /**
         * 状态：1-审核通过
         */
        APPROVED(1, "审核通过"),
        /**
         * 状态：2-审核拒绝
         */
        REJECTED(2, "审核拒绝");

        @EnumValue
        private final int value;
        private final String name;

        ReviewStatusEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 提成比例
     */
    private Integer deductpercert;
    /**
     * 是否同步库存,0-不同步，1-同步
     */
    private Integer syncstock;
    /**
     * 是否同步价格,0-不同步，1-同步
     */
    private Integer syncprice;
    /**
     * 商品名称首拼字母
     */
    private String cwcode;
    /**
     * 条形码编号
     */
    private String barcode;
    /**
     * 是否已删除，1-是，0-否
     */
    private Boolean sdelete;
    /**
     * 近效期
     */
    private Boolean effectperiod;
    /**
     * 特价开始时间
     */
    private Date stime;
    /**
     * 特价结束时间
     */
    private Date etime;
    /**
     * 过期时间
     */
    private Date expiretime;
    /**
     * 备案号
     */
    private String recordNumber;
    /**
     * 结构组成
     */
    private String structureComposition;
    /**
     * 适用范围
     */
    private String scopeApplication;
    /**
     * 生产企业许可证号
     */
    private String manufacturLicense;
    /**
     * 技术要求编号
     */
    private String technicalNumber;
    /**
     * 商品关联类型：0-无关联，1-主商品，2-关联商品
     */
    private AttributionTypeEnum attributionType;

    public enum AttributionTypeEnum {
        /**
         * 0-无关联
         */
        NONE(0, "无关联"),
        /**
         * 1-主商品
         */
        MAIN(1, "主商品"),
        /**
         * 2-关联商品
         */
        ATTRIBUTION(2, "关联商品");

        @EnumValue
        private final int value;
        private final String name;

        AttributionTypeEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 关联主商品ID
     */
    private Integer attributionId;

    // 1085【2021年1月27日】平台总仓商品信息优化,平台商品库td_product新增字段sql
    /**
     * 生产产地
     */
    private String productionAddress;

    /**
     * 性状
     */
    private String trait;

    /**
     * 药物相互作用
     */
    private String drugInteractions;

    /**
     * 贮藏
     */
    private String layInDeposit;

    /**
     * 有效期
     */
    private String inDate;

    /**
     * 关键词
     */
    private String keyword;

}