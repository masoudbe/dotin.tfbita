import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';

export interface IJustificationDeductionAmountPart {
  id?: number;
  receiveTransactionCode?: string | null;
  returnTransactionCode?: string | null;
  date?: string | null;
  amount?: number | null;
  amountBasedOnRial?: number | null;
  depositNumber?: string | null;
  receiveCurrencyCode?: string | null;
  currencyRateDate?: string | null;
  sellRate?: number | null;
  buyRate?: number | null;
  comment?: string | null;
  justificationDeductionAmount?: IJustificationDeductionAmount | null;
}

export const defaultValue: Readonly<IJustificationDeductionAmountPart> = {};
