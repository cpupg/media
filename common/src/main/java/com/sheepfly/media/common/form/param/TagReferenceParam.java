package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TagReferenceParam extends TablePagination {
    private String id;
    private String resourceId;
    private String tagId;
    private String tagName;
    private boolean rate;
    private boolean favorite;
}
