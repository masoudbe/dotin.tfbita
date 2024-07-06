import { ICategoryElement } from 'app/shared/model/category-element.model';
import { IDraftTypeTopicInfo } from 'app/shared/model/draft-type-topic-info.model';
import { ICreditTypeConditionInfo } from 'app/shared/model/credit-type-condition-info.model';
import { IDraftTypeAccountInfo } from 'app/shared/model/draft-type-account-info.model';
import { IDraftRequestType } from 'app/shared/model/draft-request-type.model';
import { IObjectiveCategoryElement } from 'app/shared/model/objective-category-element.model';
import { IStringValue } from 'app/shared/model/string-value.model';

export interface IDraftType {
  id?: number;
  alarmTime?: number | null;
  code?: string | null;
  disableDate?: string | null;
  duration?: number | null;
  hasAssurance?: boolean | null;
  hasSanction?: boolean | null;
  latestCreditSerial?: number | null;
  name?: string | null;
  portal?: boolean | null;
  usable?: boolean | null;
  currenciesCodes?: string | null;
  defaultCurrencyCode?: string | null;
  type?: ICategoryElement | null;
  secondaryType?: ICategoryElement | null;
  division?: ICategoryElement | null;
  topicInfo?: IDraftTypeTopicInfo | null;
  conditionInfo?: ICreditTypeConditionInfo | null;
  accountInfo?: IDraftTypeAccountInfo | null;
  requestType?: IDraftRequestType | null;
  acceptableProductTypes?: IObjectiveCategoryElement | null;
  userGroups?: IStringValue[] | null;
}

export const defaultValue: Readonly<IDraftType> = {
  hasAssurance: false,
  hasSanction: false,
  portal: false,
  usable: false,
};
