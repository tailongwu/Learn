package ch12

type VirtualWalletRepo interface {
	QueryById(walletId int64) (VirtualWallet, error)
	Save(wallet VirtualWallet) error
}

type VirtualWalletService struct {
	repo VirtualWalletRepo
}

func (v *VirtualWalletService) Debit(walletId int64, amount float64) error {
	wallet, err := v.repo.QueryById(walletId)
	if err != nil {
		return err
	}

	err = wallet.Debit(amount)
	if err != nil {
		return err
	}

	return v.repo.Save(wallet)
}

func (v *VirtualWalletService) Credit(walletId int64, amount float64) error {
	wallet, err := v.repo.QueryById(walletId)
	if err != nil {
		return err
	}

	err = wallet.Credit(amount)
	if err != nil {
		return err
	}

	return v.repo.Save(wallet)
}

func (v *VirtualWalletService) Transfer(fromWalletId int64, toWalletId int64, amount float64) error {
	// transaction
	if err := v.Debit(fromWalletId, amount); err != nil {
		return err
	}
	if err := v.Credit(toWalletId, amount); err != nil {
		return err
	}

	return nil
}