package ch12

import "errors"

type VirtualWallet struct {
	Id         int64
	CreateTime int64
	Balance    float64
}

func (v *VirtualWallet) Debit(amount float64) error {
	if v.Balance < amount {
		return errors.New("...")
	}
	v.Balance -= amount
	return nil
}

func (v *VirtualWallet) Credit(amount float64) error {
	if amount < 0 {
		return errors.New("...")
	}
	v.Balance += amount
	return nil
}
