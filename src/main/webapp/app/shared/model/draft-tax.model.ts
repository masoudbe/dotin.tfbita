import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftTax {
  id?: number;
  orderRegCurrencyAmount?: number | null;
  mainAccountCurrencyAmount?: number | null;
  letterNumber?: string | null;
  letterImageContentType?: string | null;
  letterImage?: string | null;
  description?: string | null;
  registrationDate?: string | null;
  returnTaxesAmount?: boolean | null;
  orderRegRate?: number | null;
  mainAccountRate?: number | null;
  documentTransactionNumber?: string | null;
  returnDocumentTransactionNumber?: string | null;
  taxes?: IDraft | null;
}

export const defaultValue: Readonly<IDraftTax> = {
  returnTaxesAmount: false,
};
