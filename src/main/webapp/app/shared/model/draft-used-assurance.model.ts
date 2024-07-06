import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftUsedAssurance {
  id?: number;
  assuranceRateId?: string | null;
  assuranceSerial?: string | null;
  exchangeRate?: number | null;
  defaultCurrencyUsedCost?: number | null;
  usedCost?: number | null;
  draft?: IDraft | null;
}

export const defaultValue: Readonly<IDraftUsedAssurance> = {};
