import { IDraft } from 'app/shared/model/draft.model';
import { IDraftCustomJustification } from 'app/shared/model/draft-custom-justification.model';

export interface IDraftReceipt {
  id?: number;
  comment?: string | null;
  customerDeliverDate?: string | null;
  date?: string | null;
  deleteDate?: string | null;
  deliverDate?: string | null;
  deliverDuration?: number | null;
  delivered?: boolean | null;
  documentTransactionEffectiveDate?: string | null;
  freightLetterDate?: string | null;
  freightLetterNumber?: string | null;
  freightLetterStampCost?: number | null;
  issueDate?: string | null;
  issueDocument?: boolean | null;
  productAmount?: number | null;
  receiptAmount?: number | null;
  receiptDate?: string | null;
  row?: number | null;
  serial?: string | null;
  transportRow?: string | null;
  usable?: boolean | null;
  paymentCurrencyRateTypeDesc?: string | null;
  paymentItemTypeDesc?: string | null;
  netWeight?: number | null;
  grossWeight?: number | null;
  totalNetWeight?: number | null;
  totalGrossWeight?: number | null;
  letterNumberTazirat?: string | null;
  letterDateTazirat?: string | null;
  fobAmount?: number | null;
  shippingFare?: number | null;
  inspectionCost?: number | null;
  discount?: number | null;
  deadlineSubmitDocumentDate?: string | null;
  currencyProvisionFileContentType?: string | null;
  currencyProvisionFile?: string | null;
  isMigrational?: boolean | null;
  otherCost?: number | null;
  receipts?: IDraft | null;
  draftCustomJustifications?: IDraftCustomJustification[] | null;
}

export const defaultValue: Readonly<IDraftReceipt> = {
  delivered: false,
  issueDocument: false,
  usable: false,
  isMigrational: false,
};
