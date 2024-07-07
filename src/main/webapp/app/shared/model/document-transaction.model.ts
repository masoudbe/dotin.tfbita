import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';

export interface IDocumentTransaction {
  id?: number;
  currencyExchangeCode?: string | null;
  transactionCode?: string | null;
  otherDocumentTransactionsContainer?: IDraftDocumentTransactionContainer | null;
  canceledJustificationDocumentContainer?: IDraftDocumentTransactionContainer | null;
  justificationDocumentTransactionsContainer?: IDraftDocumentTransactionContainer | null;
  receivedCommisionsContainer?: IDraftDocumentTransactionContainer | null;
  canceledDocumentTransactionsContainer?: IDraftDocumentTransactionContainer | null;
  returnedDefaultCurrencyCostsContainer?: IDraftDocumentTransactionContainer | null;
  defaultCurrencyCostsDocumentContainer?: IDraftDocumentTransactionContainer | null;
  customJustifications?: ICustomJustification[] | null;
}

export const defaultValue: Readonly<IDocumentTransaction> = {};
