export interface IDraftTypeAccountInfo {
  id?: number;
  sellCurrencyCommissionAccount?: string | null;
  incomeAccountNumber?: string | null;
}

export const defaultValue: Readonly<IDraftTypeAccountInfo> = {};
