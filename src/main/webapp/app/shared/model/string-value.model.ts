import { IDraft } from 'app/shared/model/draft.model';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';

export interface IStringValue {
  id?: number;
  val?: string | null;
  drafts?: IDraft[] | null;
  draftTypes?: IDraftType[] | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
}

export const defaultValue: Readonly<IStringValue> = {};
