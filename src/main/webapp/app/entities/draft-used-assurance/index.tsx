import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftUsedAssurance from './draft-used-assurance';
import DraftUsedAssuranceDetail from './draft-used-assurance-detail';
import DraftUsedAssuranceUpdate from './draft-used-assurance-update';
import DraftUsedAssuranceDeleteDialog from './draft-used-assurance-delete-dialog';

const DraftUsedAssuranceRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftUsedAssurance />} />
    <Route path="new" element={<DraftUsedAssuranceUpdate />} />
    <Route path=":id">
      <Route index element={<DraftUsedAssuranceDetail />} />
      <Route path="edit" element={<DraftUsedAssuranceUpdate />} />
      <Route path="delete" element={<DraftUsedAssuranceDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftUsedAssuranceRoutes;
