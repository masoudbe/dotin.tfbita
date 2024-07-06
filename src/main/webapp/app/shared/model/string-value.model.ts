import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IDraft } from 'app/shared/model/draft.model';
import { IDraftType } from 'app/shared/model/draft-type.model';

export interface IStringValue {
  id?: number;
  val?: string | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
  drafts?: IDraft[] | null;
  draftTypes?: IDraftType[] | null;
}

export const defaultValue: Readonly<IStringValue> = {};
