export interface IDraftTypeTopicInfo {
  id?: number;
  currencySellCommissionTopic?: number | null;
  documentReceiptDisciplinaryTopic?: number | null;
  draftMainTopic?: number | null;
  insuranceCostTopic?: number | null;
  justificationDisciplinaryTopic?: number | null;
  openDraftDisciplinaryTopic?: number | null;
  otherCostsTopic?: number | null;
  postTelegraphSwiftCostsTopic?: number | null;
}

export const defaultValue: Readonly<IDraftTypeTopicInfo> = {};
