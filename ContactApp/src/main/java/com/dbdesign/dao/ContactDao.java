package com.dbdesign.dao;

import java.sql.SQLException;
import java.util.List;

import com.dbdesign.model.Contact;


public interface ContactDao {
	
	 public void saveOrUpdate(Contact contact);
     
	    public int delete(int contactId) throws SQLException;
	     
	    public Contact get(int contactId);
	     
	    public List<Contact> list() throws SQLException;

		public Contact edit(int id) throws SQLException;

		public void update(Contact contact) throws SQLException;

		public void insert(Contact contact) throws SQLException;

		public List<Contact> search(String str) throws SQLException;

}
