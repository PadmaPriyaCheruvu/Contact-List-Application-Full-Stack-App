package com.dbdesign.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dbdesign.model.Address;
import com.dbdesign.model.Contact;
import com.dbdesign.model.Date;
import com.dbdesign.model.Phone;



public class ContactDaoImpl implements ContactDao{
	
	Contact editContact = new Contact();
	private JdbcTemplate jdbcTemplate;
	 
    public ContactDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	 
    //Delete Contact
	public int delete(int contactId) throws SQLException {
		// TODO Auto-generated method stub
		String sql1 = "delete from Phone where contact_id=?";
		String sql2 = "delete  from DateTable where contact_id=?";
		String sql3 = "delete  from Address where contact_id=?";
		String sql4 = "delete  from Contact where contact_id=?";
		
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        PreparedStatement stmt=con.prepareStatement(sql1);  
        stmt.setInt(1, contactId);
        stmt.executeUpdate();
        stmt=con.prepareStatement(sql2);
        stmt.setInt(1, contactId);
        stmt.executeUpdate();
        stmt=con.prepareStatement(sql3);
        stmt.setInt(1, contactId);
        stmt.executeUpdate();
        stmt=con.prepareStatement(sql4);
        stmt.setInt(1, contactId);
        stmt.executeUpdate();
		return contactId;
	}
	
	//Insert Contact
	public void insert(Contact contact) throws SQLException {
		// TODO Auto-generated method stub
		String sql1 = "INSERT INTO Contact (fname,mname,lname) VALUES (?,?,?);";
		String sql2 = "INSERT INTO Address (contact_id, address_type,address,city,state,zip) VALUES ((SELECT contact_id FROM Contact WHERE fname=? AND mname=? AND lname=?), ?,?,?,?,?);";
		String sql3 = "INSERT INTO Phone (contact_id, phone_type, area_code,ph_number) VALUES ((SELECT contact_id FROM Contact WHERE fname=? AND mname=? AND lname=?), ?,?,?);";
		String sql4 = "INSERT INTO DateTable (contact_id, date_type, date_value) VALUES((SELECT contact_id FROM Contact WHERE fname=? AND mname=? AND lname=?),?,STR_TO_DATE(?, '%m/%d/%Y'));";
		
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        PreparedStatement stmt=con.prepareStatement(sql1);  
        stmt.setString(1, contact.getFname());
        stmt.setString(2, contact.getMname());
        stmt.setString(3, contact.getLname());
        stmt.executeUpdate();
        
        for(int i=0;i<contact.getAddresses().size();i++)
        {
        stmt=con.prepareStatement(sql2);
        stmt.setString(1, contact.getFname());
        stmt.setString(2, contact.getMname());
        stmt.setString(3, contact.getLname());
        stmt.setString(4, contact.getAddresses().get(i).getAddrType());
        stmt.setString(5, contact.getAddresses().get(i).getAddr());
        stmt.setString(6, contact.getAddresses().get(i).getCity());
        stmt.setString(7, contact.getAddresses().get(i).getState());
        stmt.setString(8, contact.getAddresses().get(i).getZip());
        stmt.executeUpdate();
        }
        
        for(int i=0;i<contact.getPhones().size();i++)
        {
        stmt=con.prepareStatement(sql3);
        stmt.setString(1, contact.getFname());
        stmt.setString(2, contact.getMname());
        stmt.setString(3, contact.getLname());
        stmt.setString(4, contact.getPhones().get(i).getPhType());
        stmt.setString(5, contact.getPhones().get(i).getAreaCode());
        stmt.setString(6, contact.getPhones().get(i).getNumber());
        stmt.executeUpdate();
        }
        
        for(int i=0;i<contact.getDates().size();i++)
        {
        stmt=con.prepareStatement(sql4);
        stmt.setString(1, contact.getFname());
        stmt.setString(2, contact.getMname());
        stmt.setString(3, contact.getLname());
        stmt.setString(4, contact.getDates().get(i).getDtType());
        stmt.setString(5, contact.getDates().get(i).getDateVal());
        stmt.executeUpdate();
        }

	}

	public Contact get(int contactId) {
		// TODO Auto-generated method stub
		return null;
	}

	//Return list of contacts
	public List<Contact> list() throws SQLException {
		String sql = "select contact_id,fname,mname, lname from Contact";
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        
	    List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {
	 
	        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Contact aContact = new Contact();
	 
	            aContact.setId(rs.getInt("contact_id"));
	            aContact.setFname(rs.getString("fname"));
	            aContact.setMname(rs.getString("mname"));
	            aContact.setLname(rs.getString("lname"));
	            
	            String sql = "select * from DateTable where contact_id = "+rs.getInt("contact_id");
				
	            PreparedStatement stmt=con.prepareStatement("select address_type,address,city,state,zip from Address where contact_id = "+rs.getInt("contact_id"));  
	            ResultSet result=stmt.executeQuery();  
	            List<Address> address = new ArrayList<Address>();
	            while(result.next()){  
	            	
	            	Address a = new Address();
	            	a.setAddrType(result.getString(1));
	            	a.setAddr(result.getString(2));
	            	a.setCity(result.getString(3));
	            	a.setState(result.getString(4));
	            	a.setZip(result.getString(5));
	            	address.add(a);
	            }
	            aContact.setAddresses(address);
	            
	            stmt=con.prepareStatement("select phone_type, area_code, ph_number from Phone where contact_id = "+rs.getInt("contact_id"));  
	            result=stmt.executeQuery();  
	            List<Phone> phone= new ArrayList<>();
	            while(result.next()){  
	            		Phone p = new Phone();
	            		p.setPhType(result.getString(1));
	            		p.setAreaCode(result.getString(2));
	            		p.setNumber(result.getString(3));
	            		phone.add(p);
	            }
	            
	           aContact.setPhones(phone);
	            
	            stmt=con.prepareStatement("select date_type, date_value from DateTable where contact_id = "+rs.getInt("contact_id"));  
	            result=stmt.executeQuery(); 
	            List<Date> date = new ArrayList<>();
	            while(result.next()){  
	            	Date d = new Date();
	            	d.setDtType(result.getString(1));
	            	d.setDateVal(result.getDate(2).toString());
	            	date.add(d);
	            }
	            aContact.setDates(date);
	            
	            return aContact;
	        }
	 
	    });
	    con.close();
	    return listContact;
	    }
	
	//Fetches the record to be edited and displays on the Edit screen
	public Contact edit(int contactId) throws SQLException {
		String sql1 = "select fname, mname, lname from Contact where contact_id = "+contactId;
		String sql2 = "select phone_type, area_code, ph_number from Phone where contact_id="+contactId;
		String sql3 = "select date_type, date_value from DateTable where contact_id="+contactId;
		String sql4 = "select address_type,address,city,state,zip from Address where contact_id="+contactId;
		
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        
        PreparedStatement stmt=con.prepareStatement(sql1);  
        ResultSet rs = stmt.executeQuery();
        if(rs.next())
        {
        	editContact.setId(contactId);
        	editContact.setFname(rs.getString("fname"));
        	editContact.setMname(rs.getString("mname"));
        	editContact.setLname(rs.getString("lname"));
        }
        
        stmt=con.prepareStatement(sql2);
        rs = stmt.executeQuery();
        List<Phone> phone= new ArrayList<>();
        while(rs.next()){
        Phone p = new Phone();
        p.setPhType(rs.getString(1));
        p.setAreaCode(rs.getString(1));
        p.setNumber(rs.getString(1));
        phone.add(p);
        }
        editContact.setPhones(phone);
        
        stmt=con.prepareStatement(sql3);
        rs = stmt.executeQuery();
        List<Date> date = new ArrayList<>();
        while(rs.next()){  
        	Date d = new Date();
        	d.setDtType(rs.getString(1));
        	d.setDateVal(rs.getDate(2).toString());
        	date.add(d);
        }
        editContact.setDates(date);
               
        stmt=con.prepareStatement(sql4);
        rs = stmt.executeQuery();
        List<Address> address = new ArrayList<Address>();
        while(rs.next()){  
        	
        	Address a = new Address();
        	a.setAddrType(rs.getString(1));
        	a.setAddr(rs.getString(2));
        	a.setCity(rs.getString(3));
        	a.setState(rs.getString(4));
        	a.setZip(rs.getString(5));
        	address.add(a);
        }
        editContact.setAddresses(address);
        
		return editContact;
		
		}
	
	//Updates the record
	public void update(Contact contact) throws SQLException {
		String sql1 = "update Contact set fname=?, mname=?, lname=? where contact_id = ?";

		
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        
        PreparedStatement stmt=con.prepareStatement(sql1);
        stmt.setInt(1, contact.getId());
        stmt.setString(2, contact.getFname());
        stmt.setString(3, contact.getMname());
        stmt.setString(4, contact.getLname());
        stmt.executeUpdate();
        
        sql1 = "update Address set address_type=?,addr=?,city=?,state=?,zip=? where contact_id = ?";
        
        List<Address> add = contact.getAddresses();
        for(int i=0;i<contact.getAddresses().size();i++)
        {
        	stmt=con.prepareStatement(sql1);
            stmt.setString(1, add.get(i).getAddrType());
            stmt.setString(2, add.get(i).getAddr());
            stmt.setString(3, add.get(i).getCity());
            stmt.setString(4, add.get(i).getState());
            stmt.setString(5, add.get(i).getZip());
            stmt.setInt(6, contact.getId());
            stmt.executeUpdate();
        }
        sql1 = "update Phone set phone_type=?, area_code=?, ph_number=? where contact_id = ?";
        
        List<Phone> ph = contact.getPhones();
        for(int i=0;i<contact.getPhones().size();i++)
        {
        	stmt=con.prepareStatement(sql1);
            stmt.setString(1, ph.get(i).getPhType());
            stmt.setString(2, ph.get(i).getAreaCode());
            stmt.setString(3, ph.get(i).getNumber());
            stmt.setInt(4, contact.getId());
            stmt.executeUpdate();
        }
        
        sql1 = "update DateTable set date_type=?, date_value=? where contact_id = ?";
        List<Date> dt = contact.getDates();
        for(int i=0;i<contact.getPhones().size();i++)
        {
        	stmt=con.prepareStatement(sql1);
            stmt.setString(1, dt.get(i).getDtType());
            stmt.setString(2, dt.get(i).getDateVal());
            stmt.setInt(3, contact.getId());
            stmt.executeUpdate();
        }
	}
	
	//Search using given string and return all related contacts
	public List<Contact> search(String str) throws SQLException {
		str.replace(' ', '%');
		String sql = "select distinct C.contact_id as con from ((DateTable D join Address A) join Phone P) join Contact C where concat( C.fname,' ',C.mname,' ',C.lname,' ',A.address_type,' ', A.address,' ', A.city,' ', A.state,' ', A.zip,' ', P.phone_type,' ', P.area_code,' ', P.ph_number,' ', D.date_type,' ',D.date_value) like '%"+str+"%'";
		try {Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException e) {	e.printStackTrace();}
        Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=latin1","root","Padma@3296");
        
		PreparedStatement stmt=con.prepareStatement(sql);  
		ResultSet result=stmt.executeQuery();  
		List<Contact> conList = list();
		List<Contact> newList = new ArrayList<>();
		while(result.next()){  
         	for(int i=0;i<conList.size();i++)
         	{
         		if(conList.get(i).getId()==result.getInt("con"))
         			newList.add(conList.get(i));
         	}
         }
         
         
		return newList;
		
		}

	
	  public void saveOrUpdate(Contact contact) { if (contact.getId() > 0) { //
	   }
	  
	  }
}
