import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftCustomJustification {
  id?: number;
  draftReceipts?: IDraftReceipt[] | null;
  draft?: IDraft | null;
}

export const defaultValue: Readonly<IDraftCustomJustification> = {};
