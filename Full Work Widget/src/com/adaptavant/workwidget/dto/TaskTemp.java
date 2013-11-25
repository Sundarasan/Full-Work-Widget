package com.adaptavant.workwidget.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.adaptavant.workwidget.utils.CustomDateSerializer;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class TaskTemp implements Serializable {
	
	private static final long serialVersionUID = -6274633250011669728L;

	public TaskTemp() {
		
	}
	
	public TaskTemp(String taskID, String type, String status, String dueDate, String comments, String notes, Date createdDate , String assigneeID, 
				String ownerID, List<String> linkedAccounts, List<String> linkedContacts, List<Long> linkedDeals, List<String> linkedSubscribers, 
				List<String> linkedTags, List<String> linkedTasks, List<String> linkedDocuments, String timeZone, String accountID,String rating) {
				
		this.ID = taskID;
		this.type = type;
		this.status = status;
		this.dueDate = dueDate;
		this.comments = comments;
		this.notes = new Text(notes);
		this.createdDate = createdDate;
		this.assigneeID = assigneeID;
		this.ownerID = ownerID;
		this.linkedAccounts = linkedAccounts;
		this.linkedContacts = linkedContacts;
		this.linkedDeals = linkedDeals;
		this.linkedSubscribers = linkedSubscribers;
		this.linkedTags = linkedTags;
		this.linkedTasks = linkedTasks;
		this.linkedDocuments = linkedDocuments;
		this.timeZone = timeZone;
		this.accountID = accountID;
		this.rating = rating;
	}
	
	@PrimaryKey
	private String ID;
	
	private String type;
	
	private String status;
	
	@Persistent
	private String dueDate;
	
	private String comments;
	
	@Persistent
	private Text notes = new Text("");
	
	@Persistent
	private Date createdDate = new Date();
	
	private String assigneeID;
	
	private String ownerID;
	
	@Persistent
	private List<String> linkedAccounts;
	
	@Persistent
	private List<String> linkedContacts;
	
	@Persistent
	private List<Long> linkedDeals;
	
	@Persistent
	private List<String> linkedTasks;
	
	@Persistent
	private List<String> linkedSubscribers;
		
	@Persistent
	private List<String> linkedTags;
	
	@Persistent
	private List<String> linkedDocuments;
	
	@Persistent
	private List<String> linkedCustomFields;
						
	private String timeZone;
	
	private String accountID;
	
	private String rating;
	
	@NotPersistent
	private String mailSubject;
	
	@NotPersistent
	private String mailContent;
	
	@NotPersistent
	private String taskContent;
	
	@NotPersistent
	private String timeZoneID;
	
	@NotPersistent
	private Long UTCOffset;
	
	public List<String> getLinkedCustomFields() {
		return linkedCustomFields;
	}

	public void setLinkedCustomFields(List<String> linkedCustomFields) {
		this.linkedCustomFields = linkedCustomFields;
	}

	public List<String> getLinkedDocuments() {
		return linkedDocuments;
	}

	public void setLinkedDocuments(List<String> linkedDocuments) {
		this.linkedDocuments = linkedDocuments;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonSerialize( using = CustomDateSerializer.class )
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getNotes() {
		 return notes.getValue();
	}

	public void setNotes(String notes) {
		this.notes = new Text(notes);
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAssigneeID() {
		return assigneeID;
	}

	public void setAssigneeID(String assigneeID) {
		this.assigneeID = assigneeID;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public List<String> getLinkedAccounts() {
		return linkedAccounts;
	}

	public void setLinkedAccounts(List<String> linkedAccounts) {
		this.linkedAccounts = linkedAccounts;
	}

	public List<String> getLinkedContacts() {
		return linkedContacts;
	}

	public void setLinkedContacts(List<String> linkedContacts) {
		this.linkedContacts = linkedContacts;
	}

	public List<Long> getLinkedDeals() {
		return linkedDeals;
	}

	public void setLinkedDeals(List<Long> linkedDeals) {
		this.linkedDeals = linkedDeals;
	}

	public List<String> getLinkedTasks() {
		return linkedTasks;
	}

	public void setLinkedTasks(List<String> linkedTasks) {
		this.linkedTasks = linkedTasks;
	}

	public List<String> getLinkedSubscribers() {
		return linkedSubscribers;
	}

	public void setLinkedSubscribers(List<String> linkedSubscribers) {
		this.linkedSubscribers = linkedSubscribers;
	}

	public List<String> getLinkedTags() {
		return linkedTags;
	}

	public void setLinkedTags(List<String> linkedTags) {
		this.linkedTags = linkedTags;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTimeZoneID() {
		return timeZoneID;
	}

	public void setTimeZoneID(String timeZoneID) {
		this.timeZoneID = timeZoneID;
	}

	public Long getUTCOffset() {
		return UTCOffset;
	}

	public void setUTCOffset(Long uTCOffset) {
		UTCOffset = uTCOffset;
	}

	public String getRating() {
    		return rating;
    	}

	public void setRating( String rating ) {
    		this.rating = rating;
    	}
}