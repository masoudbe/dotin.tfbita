import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftCertificateType from './draft-certificate-type';
import DraftCertificateTypeDetail from './draft-certificate-type-detail';
import DraftCertificateTypeUpdate from './draft-certificate-type-update';
import DraftCertificateTypeDeleteDialog from './draft-certificate-type-delete-dialog';

const DraftCertificateTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftCertificateType />} />
    <Route path="new" element={<DraftCertificateTypeUpdate />} />
    <Route path=":id">
      <Route index element={<DraftCertificateTypeDetail />} />
      <Route path="edit" element={<DraftCertificateTypeUpdate />} />
      <Route path="delete" element={<DraftCertificateTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftCertificateTypeRoutes;
