import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftDocumentTransactionContainer {
  id?: number;
  issueCommissionDocumentTransaction?: IDocumentTransaction | null;
  paymentDocumentTransaction?: IDocumentTransaction | null;
  settleDocumentTransaction?: IDocumentTransaction | null;
  settleExcessDocumentTransaction?: IDocumentTransaction | null;
  commissionDeleteDraftDocumentTransaction?: IDocumentTransaction | null;
  commissionDraftExtendDocumentTransaction?: IDocumentTransaction | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<IDraftDocumentTransactionContainer> = {};
