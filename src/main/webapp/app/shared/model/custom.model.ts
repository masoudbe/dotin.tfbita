import { IDraft } from 'app/shared/model/draft.model';

export interface ICustom {
  id?: number;
  modificationDate?: string | null;
  latinName?: string | null;
  name?: string | null;
  tempId?: number | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<ICustom> = {};
