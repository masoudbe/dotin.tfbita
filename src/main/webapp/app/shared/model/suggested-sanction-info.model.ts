import { IDraft } from 'app/shared/model/draft.model';

export interface ISuggestedSanctionInfo {
  id?: number;
  sanctionSerial?: string | null;
  personnelCode?: string | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<ISuggestedSanctionInfo> = {};
