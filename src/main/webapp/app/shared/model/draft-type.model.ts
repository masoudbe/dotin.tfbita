import { IDraft } from 'app/shared/model/draft.model';

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
  defaultCurrencyCode?: string | null;
  accountInfoCode?: string | null;
  topicInfoCode?: string | null;
  draftType?: IDraft | null;
}

export const defaultValue: Readonly<IDraftType> = {
  hasAssurance: false,
  hasSanction: false,
  portal: false,
  usable: false,
};
