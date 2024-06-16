import { IDraft } from 'app/shared/model/draft.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface ICustom {
  id?: number;
  modificationDate?: string | null;
  latinName?: string | null;
  name?: string | null;
  tempId?: number | null;
  loadSwitchPlace?: IDraft | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
  drafts?: IDraft[] | null;
}

export const defaultValue: Readonly<ICustom> = {};
