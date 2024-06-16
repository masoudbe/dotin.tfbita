import { IDraft } from 'app/shared/model/draft.model';

export interface IServiceTariff {
  id?: number;
  code?: string | null;
  title?: string | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<IServiceTariff> = {};
