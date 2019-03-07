package com.backend.daos;

import java.util.List;

import com.backend.models.Address;

public interface AddressDao {
	
	public boolean insertAddress(Address address);
	public Address getAddressById(int addressId);
	public void updateAddress(Address address);
	public void deleteAddress(int addressId);
	public List<Address> getAddressForUser(String customerId);

}
