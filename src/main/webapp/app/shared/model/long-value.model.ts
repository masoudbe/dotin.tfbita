import { IDraft } from 'app/shared/model/draft.model';

export interface ILongValue {
  id?: number;
  val?: number | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<ILongValue> = {};
