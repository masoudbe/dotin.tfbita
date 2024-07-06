import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import JustificationDeductionDetail from './justification-deduction-detail';
import JustificationDeductionDetailDetail from './justification-deduction-detail-detail';
import JustificationDeductionDetailUpdate from './justification-deduction-detail-update';
import JustificationDeductionDetailDeleteDialog from './justification-deduction-detail-delete-dialog';

const JustificationDeductionDetailRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<JustificationDeductionDetail />} />
    <Route path="new" element={<JustificationDeductionDetailUpdate />} />
    <Route path=":id">
      <Route index element={<JustificationDeductionDetailDetail />} />
      <Route path="edit" element={<JustificationDeductionDetailUpdate />} />
      <Route path="delete" element={<JustificationDeductionDetailDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JustificationDeductionDetailRoutes;
