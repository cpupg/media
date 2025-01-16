package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源。
 *
 * @author sheepfly
 */
public class ResourceVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 资源目录
     */
    private String dir;
    /**
     * 目录代码。
     */
    private Long dirCode;

    /**
     * 作者。
     */
    private AuthorVo authorVo;

    /**
     * 专辑。
     */
    private AlbumVo albumVo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 资源拥有的标签。
     */
    private List<TagReferenceVo> tagReferenceVoList;
    /**
     * 标签数量。
     */
    private Long tagCount;
    /**
     * 收藏。
     */
    private boolean favorite;
    /**
     * 评分。
     */
    private int rate;
    /**
     * 封面文件id。
     */
    private String coverId;
    /**
     * 和资源关联的专辑id，只在ResourceSelectModal中使用。
     */
    private String albumId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return this.dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Long getDirCode() {
        return this.dirCode;
    }

    public void setDirCode(Long dirCode) {
        this.dirCode = dirCode;
    }

    public AuthorVo getAuthorVo() {
        return this.authorVo;
    }

    public void setAuthorVo(AuthorVo authorVo) {
        this.authorVo = authorVo;
    }

    public AlbumVo getAlbumVo() {
        return this.albumVo;
    }

    public void setAlbumVo(AlbumVo albumVo) {
        this.albumVo = albumVo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<TagReferenceVo> getTagReferenceVoList() {
        return this.tagReferenceVoList;
    }

    public void setTagReferenceVoList(List<TagReferenceVo> tagReferenceVoList) {
        this.tagReferenceVoList = tagReferenceVoList;
    }

    public Long getTagCount() {
        return this.tagCount;
    }

    public void setTagCount(Long tagCount) {
        this.tagCount = tagCount;
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCoverId() {
        return this.coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
