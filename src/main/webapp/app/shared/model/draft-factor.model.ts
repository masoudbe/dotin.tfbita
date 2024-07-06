import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftFactor {
  id?: number;
  amount?: number | null;
  description?: string | null;
  eqAmount?: number | null;
  factorDate?: string | null;
  issueDate?: string | null;
  serial?: string | null;
  currencyCode?: string | null;
  draft?: IDraft | null;
}

export const defaultValue: Readonly<IDraftFactor> = {};
