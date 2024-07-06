import { IDraftRequestType } from 'app/shared/model/draft-request-type.model';

export interface IDraftCertificateType {
  id?: number;
  mandatory?: boolean | null;
  draftRequestType?: IDraftRequestType | null;
}

export const defaultValue: Readonly<IDraftCertificateType> = {
  mandatory: false,
};
