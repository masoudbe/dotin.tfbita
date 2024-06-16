import { IDraft } from 'app/shared/model/draft.model';

export interface IDraftStatusInfo {
  id?: number;
  approved?: boolean | null;
  draftRequestAccepted?: boolean | null;
  insuranceCostPaid?: boolean | null;
  issued?: boolean | null;
  otherCostsPaid?: boolean | null;
  postSwiftCostPaied?: boolean | null;
  rejectDescription?: string | null;
  remainAmount?: number | null;
  stampCostPaid?: boolean | null;
  statusInfo?: IDraft | null;
}

export const defaultValue: Readonly<IDraftStatusInfo> = {
  approved: false,
  draftRequestAccepted: false,
  insuranceCostPaid: false,
  issued: false,
  otherCostsPaid: false,
  postSwiftCostPaied: false,
  stampCostPaid: false,
};
