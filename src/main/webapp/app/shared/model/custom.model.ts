import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface ICustom {
  id?: number;
  modificationDate?: string | null;
  latinName?: string | null;
  name?: string | null;
  tempId?: number | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<ICustom> = {};
