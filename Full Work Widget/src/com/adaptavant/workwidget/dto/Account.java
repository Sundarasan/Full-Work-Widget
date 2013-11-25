package com.adaptavant.workwidget.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

public class Account implements Serializable
	{
		/**
	 * 
	 */
		private static final long	serialVersionUID	= -2178591816532684514L;

		private String ID;
		
		private String brandID;
		
		private String name;
		
		private Boolean deleted;
		
		private Double value;
		
		private String rating;
		
		private String status;
		
		private String ownerID;
		
		private String source;

		private Date createdDate = new Date();
		 
		private Date lastUpdatedDate = new Date();
		
		private List<String> linkedContacts;
		
		private List<String> linkedTasks;
		
		private List<String> linkedHistory;

		private Boolean billed;
		
		private List<String> linkedTags;
		
		private List<String> linkedDocuments;
		
		private List<String> linkedProducts;
		
		private List<String> linkedCustomFields;
			
		private String parentAccountID;
		
		private Double documentsSpace;
		
		private Text notes = new Text("");
		
		@Persistent
		private List<String> linkedSubscribers;

		public List<String> getLinkedCustomFields() {
			return linkedCustomFields;
		}

		public void setLinkedCustomFields(List<String> linkedCustomFields) {
			this.linkedCustomFields = linkedCustomFields;
		}

		public List<String> getLinkedProducts() {
			return linkedProducts;
		}

		public void setLinkedProducts(List<String> linkedProducts) {
			this.linkedProducts = linkedProducts;
		}

		public Double getDocumentsSpace() {
			return documentsSpace;
		}

		public void setDocumentsSpace(Double documentsSpace) {
			this.documentsSpace = documentsSpace;
		}

		public String getID() {
			return ID;
		}

		public void setID(String accountID) {
			this.ID = accountID;
		}

		public String getParentAccountID() {
			return parentAccountID;
		}

		public void setParentAccountID(String parentAccountID) {
			this.parentAccountID = parentAccountID;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public Boolean isDeleted() {
			return deleted;
		}

		public void setDeleted( Boolean deleted ) {
			this.deleted = deleted;
		}
		
		public Double getValue() {
			return value;
		}

		public void setValue(Double value) {
			this.value = value;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getOwnerID() {
			return ownerID;
		}

		public void setOwnerID(String ownerID) {
			this.ownerID = ownerID;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Date getLastUpdatedDate() {
			return lastUpdatedDate;
		}

		public void setLastUpdatedDate(Date lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
		}

		public List<String> getLinkedContacts() {
			return linkedContacts;
		}

		public void setLinkedContacts(List<String> linkedContacts ) {
			this.linkedContacts = linkedContacts;
		}

		public List<String> getLinkedTasks() {
			return linkedTasks;
		}

		public void setLinkedTasks(List<String> linkedTasks) {
			this.linkedTasks = linkedTasks;
		}

		public List<String> getLinkedHistory() {
			return linkedHistory;
		}

		public void setLinkedHistory(List<String> linkedHistory) {
			this.linkedHistory = linkedHistory;
		}
		
		public Boolean isBilled() {
			return billed;
		}

		public void setBilled( Boolean billed ) {
			this.billed = billed;
		}

		public List<String> getLinkedTags() {
			return linkedTags;
		}

		public void setLinkedTags(List<String> linkedTags) {
			this.linkedTags = linkedTags;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getBrandID() {
        		return brandID;
        	}

		public void setBrandID( String brandID ) {
        		this.brandID = brandID;
        	}

		public List<String> getLinkedDocuments() {
			return linkedDocuments;
		}

		public void setLinkedDocuments(List<String> linkedDocuments) {
			this.linkedDocuments = linkedDocuments;
		}
		
		public String getNotes() {
			return notes.getValue();
		}

		public void setNotes(String notes) {
			this.notes = new Text(notes);
		}
		
		public List<String> getLinkedSubscribers() {
			return linkedSubscribers;
		}

		public void setLinkedSubscribers(List<String> linkedSubscribers) {
			this.linkedSubscribers = linkedSubscribers;
		}		
	}