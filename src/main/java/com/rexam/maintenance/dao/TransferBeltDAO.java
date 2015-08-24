package com.rexam.maintenance.dao;

import com.rexam.maintenance.model.TransferBeltModel;

public interface TransferBeltDAO {
	
	public TransferBeltModel transferBeltReturnEntryByID(int idIn);
	public void transferBeltUpdate(TransferBeltModel tm);
	
}
