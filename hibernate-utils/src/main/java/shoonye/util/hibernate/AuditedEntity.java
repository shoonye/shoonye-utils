package shoonye.util.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <I> Entitye Type
 */
@MappedSuperclass
@Audited
public abstract class AuditedEntity<I  extends Serializable> extends BaseEntity<I> {
	private static final long serialVersionUID = 1L;
	
	@Column(name="created_time") @Type(type = "shoonye.util.hibernate.JodaDateType") private DateTime createdTime = new DateTime();
	@Column(name="created_by", length=100) 	private String createdBy;
	@Column(name="modified_time") @Type(type = "shoonye.util.hibernate.JodaDateType") private DateTime modifiedTime;
	@Column(name="modified_by", length=100) private String modifiedBy;
	
	public DateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(DateTime createDate) {
		this.createdTime = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public DateTime getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(DateTime modifiedDate) {
		this.modifiedTime = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}	
}
