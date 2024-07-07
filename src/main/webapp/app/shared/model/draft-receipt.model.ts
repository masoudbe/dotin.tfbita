import { ICategoryElement } from 'app/shared/model/category-element.model';
import { IPaymentCurrencyRateType } from 'app/shared/model/payment-currency-rate-type.model';
import { IPaymentItemType } from 'app/shared/model/payment-item-type.model';
import { IDraftReceiptDocumentTransactionContainer } from 'app/shared/model/draft-receipt-document-transaction-container.model';
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
  productDimension?: ICategoryElement | null;
  stateOfDocuments?: ICategoryElement | null;
  currencyProvisionFileType?: ICategoryElement | null;
  paymentCurrencyRateType?: IPaymentCurrencyRateType | null;
  paymentItem?: IPaymentItemType | null;
  documentTransactionContainer?: IDraftReceiptDocumentTransactionContainer | null;
  draft?: IDraft | null;
  draftCustomJustifications?: IDraftCustomJustification[] | null;
}

export const defaultValue: Readonly<IDraftReceipt> = {
  delivered: false,
  issueDocument: false,
  usable: false,
  isMigrational: false,
};
