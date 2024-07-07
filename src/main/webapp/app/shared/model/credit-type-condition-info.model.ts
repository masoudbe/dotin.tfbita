import { ICreditTypeCondition } from 'app/shared/model/credit-type-condition.model';

export interface ICreditTypeConditionInfo {
  id?: number;
  durationFrom?: number | null;
  durationTo?: number | null;
  priceFrom?: number | null;
  priceTo?: number | null;
  justificationDisciplinaryTopic?: number | null;
  openDraftDisciplinaryTopic?: number | null;
  otherCostsTopic?: number | null;
  postTelegraphSwiftCostsTopic?: number | null;
  defaultCondition?: ICreditTypeCondition | null;
}

export const defaultValue: Readonly<ICreditTypeConditionInfo> = {};
