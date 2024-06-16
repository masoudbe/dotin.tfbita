import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { IDraft } from 'app/shared/model/draft.model';
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';

export interface IProduct {
  id?: number;
  code?: string | null;
  modificationDate?: string | null;
  name?: string | null;
  description?: string | null;
  topicCode?: string | null;
  attributeValueGroupName?: string | null;
  orderRegistrationInfos?: IOrderRegistrationInfo[] | null;
  drafts?: IDraft[] | null;
  draftProductInfos?: IDraftReceipt | null;
}

export const defaultValue: Readonly<IProduct> = {};
