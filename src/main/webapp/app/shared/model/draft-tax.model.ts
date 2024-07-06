import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
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
  documentTransaction?: IDocumentTransaction | null;
  returnDocumentTransaction?: IDocumentTransaction | null;
  draft?: IDraft | null;
}

export const defaultValue: Readonly<IDraftTax> = {
  returnTaxesAmount: false,
};
