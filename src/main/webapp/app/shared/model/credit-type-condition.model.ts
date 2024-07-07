import { ICategoryElement } from 'app/shared/model/category-element.model';
import { IObjectiveCategoryElement } from 'app/shared/model/objective-category-element.model';
import { ICreditTypeConditionInfo } from 'app/shared/model/credit-type-condition-info.model';

export interface ICreditTypeCondition {
  id?: number;
  assurancePercentage?: number | null;
  commissionRate?: number | null;
  customerPrepaymentRateFrom?: number | null;
  customerPrepaymentRateTo?: number | null;
  durationFrom?: number | null;
  durationTo?: number | null;
  orderRegistrationRightFrom?: number | null;
  orderRegistrationRightTo?: number | null;
  postSuspensionPeriodPenaltyRate?: number | null;
  priceFrom?: number | null;
  priceTo?: number | null;
  suspensionDurationFrom?: number | null;
  suspensionDurationTo?: number | null;
  suspensionPeriodInterestRate?: number | null;
  updateCommissionRate?: number | null;
  currencyCode?: string | null;
  serviceOrProduct?: ICategoryElement | null;
  neededIdentificationDocTypes?: IObjectiveCategoryElement | null;
  productTypes?: IObjectiveCategoryElement | null;
  assuranceTypes?: IObjectiveCategoryElement | null;
  creditTypeConditionInfo?: ICreditTypeConditionInfo | null;
}

export const defaultValue: Readonly<ICreditTypeCondition> = {};
