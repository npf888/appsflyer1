package com.ami.mail.pojo;

import java.util.Date;

/**
 * 邮件
 * @author JavaServer
 *
 */
public class MailInfo {
	
	
	private Long id;
	private Long charId;
	private Long sendId;
	private String sendName;
	private Long recId;
	private String recName;
	private String title;
	private String content;
	private Integer mailType;
	private Integer mailStatus;
	private Integer hasAttachment;
	private String attachmentPack;
	private String updateTime;
	private String createTime;
	private Integer deleted;
	private String head;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCharId() {
		return charId;
	}
	public void setCharId(Long charId) {
		this.charId = charId;
	}
	public Long getSendId() {
		return sendId;
	}
	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public Long getRecId() {
		return recId;
	}
	public void setRecId(Long recId) {
		this.recId = recId;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getMailType() {
		return mailType;
	}
	public void setMailType(Integer mailType) {
		this.mailType = mailType;
	}
	public Integer getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(Integer mailStatus) {
		this.mailStatus = mailStatus;
	}
	public Integer getHasAttachment() {
		return hasAttachment;
	}
	public void setHasAttachment(Integer hasAttachment) {
		this.hasAttachment = hasAttachment;
	}
	public String getAttachmentPack() {
		return attachmentPack;
	}
	public void setAttachmentPack(String attachmentPack) {
		this.attachmentPack = attachmentPack;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	
	

	
	
}
