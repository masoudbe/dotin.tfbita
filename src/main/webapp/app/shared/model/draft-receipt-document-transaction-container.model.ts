import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';

export interface IDraftReceiptDocumentTransactionContainer {
  id?: number;
  receiveReceiptCommission?: boolean | null;
  receiptIssueDocumentTransaction?: IDocumentTransaction | null;
  freightLetterStampCostDocumentTransaction?: IDocumentTransaction | null;
  deliverDocumentTransaction?: IDocumentTransaction | null;
  documentTransactionCanceledDeliver?: IDocumentTransaction | null;
  documentTransactionCanceledReceiptIssue?: IDocumentTransaction | null;
  receiptCommissionDocumentTransactions?: IDocumentTransaction | null;
  draftDocumentTransactionContainer?: IDraftDocumentTransactionContainer | null;
}

export const defaultValue: Readonly<IDraftReceiptDocumentTransactionContainer> = {
  receiveReceiptCommission: false,
};
