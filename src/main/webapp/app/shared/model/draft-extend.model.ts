import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftExtend {
  id?: number;
  date?: string | null;
  duration?: number | null;
  time?: string | null;
  draftField?: IDraft | null;
}

export const defaultValue: Readonly<IDraftExtend> = {};
