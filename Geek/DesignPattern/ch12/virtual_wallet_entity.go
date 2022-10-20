package ch12

import "errors"

type VirtualWallet struct {
	Id                 int64
	CreateTime         int64
	Balance            float64
	IsAllowedOverdraft bool
	OverdraftAmount    float64
	FrozenAmount       float64
}

func (v *VirtualWallet) GetAvailableBalance() float64 {
	total := v.Balance - v.FrozenAmount
	if v.IsAllowedOverdraft {
		total += v.OverdraftAmount
	}
	return total
}

func (v *VirtualWallet) Debit(amount float64) error {
	if v.Balance < v.GetAvailableBalance() {
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
